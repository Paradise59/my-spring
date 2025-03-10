package spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 提供获取 InputStream 流的方法，接下来再分别实现三种不同的流文件操作：classPath、FileSystem、URL
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
