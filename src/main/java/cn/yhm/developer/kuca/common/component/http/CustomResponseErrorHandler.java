package cn.yhm.developer.kuca.common.component.http;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * 自定义响应错误处理程序
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-07 13:54:11
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(@NotNull ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(@NotNull ClientHttpResponse response) throws IOException {

    }

}
