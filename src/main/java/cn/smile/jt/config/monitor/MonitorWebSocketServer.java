package cn.smile.jt.config.monitor;


import cn.smile.jt.config.websocket.MyEndpointConfigure;
import cn.smile.jt.util.ErrorUtil;
import cn.smile.jt.util.SystemMonitorUtil;
import cn.smile.jt.util.ThreadPoolUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket获取实时系统监控并输出到Web页面
 * @author longjuntao
 */
@Slf4j
@Component
@ServerEndpoint(value = "/websocket/monitor", configurator = MyEndpointConfigure.class)
public class MonitorWebSocketServer {

    /**
     * 连接集合
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        //添加到集合中
        sessionMap.put(session.getId(), session);

        //获取系统监控信息
        ThreadPoolUtil.newTask(() -> {
            log.info("MonitorWebSocketServer start");
            ObjectMapper mapper = new ObjectMapper();
            //当属性的值为空（null或者""）时，不进行序列化，可以减少数据传输
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            //设置日期格式
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            while (sessionMap.get(session.getId()) != null) {
                try {
                    //获取系统监控信息 发送
                    send(session,  mapper.writeValueAsString(SystemMonitorUtil.getSysMonitor()));

                    //休眠一秒
                    Thread.sleep(1000);
                } catch (Exception e) {
                    //输出到日志文件中
                    log.error(ErrorUtil.errorInfoToString(e));
                }
            }
            log.info("MonitorWebSocketServer end");
        });
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        //从集合中删除
        sessionMap.remove(session.getId());
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("session = {}", session);
        //输出到日志文件中
        log.error(ErrorUtil.errorInfoToString(error));
    }

    /**
     * 服务器接收到客户端消息时调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("message = {}, session = {}", message, session);
    }

    /**
     * 封装一个send方法，发送消息到前端
     */
    private void send(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            //输出到日志文件中
            log.error(ErrorUtil.errorInfoToString(e));
        }
    }
}
