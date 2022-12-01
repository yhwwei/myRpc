package gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * 网络传输的一个端点
 * @author yhw
 * @version 1.0
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    private String host;
    private int port;

}

