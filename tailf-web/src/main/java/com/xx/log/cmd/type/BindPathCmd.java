package com.xx.log.cmd.type;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.websocket.Session;

@Data
@EqualsAndHashCode(callSuper = false)
public class BindPathCmd extends BaseCmd {
    /**	 * serialVersionUID	 */
	private static final long serialVersionUID = 1L;
	
	/**客户端IP*/
	private String host;
	
	/**绑定的路径*/
    private String pathKey;
    
    /**当前webSocket session*/
    private Session session;
}