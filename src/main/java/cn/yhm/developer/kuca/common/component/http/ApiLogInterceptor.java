package cn.yhm.developer.kuca.common.component.http;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Api接口日志拦截器
 * <p>
 * 用途：调用第三方接口时，打印接口的请求入参和响应出参日志
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-06 22:30:07
 */
@Slf4j
public class ApiLogInterceptor implements ClientHttpRequestInterceptor {

    /**
     * 拦截
     *
     * @param request   请求对象
     * @param body      请求体
     * @param execution 执行器
     * @return {@link ClientHttpResponse}
     * @throws IOException ioexception
     */
    @NotNull
    @Override
    public ClientHttpResponse intercept(@NotNull HttpRequest request, @NotNull byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        // 打印请求入参日志
        traceRequest(request, body);
        // 接口调用
        ClientHttpResponse response = execution.execute(request, body);
        // 打印请求响应日志
        traceResponse(response);
        return response;
    }

    /**
     * 跟踪请求
     * <p>
     * ContentType为JSON格式则打印JSON格式的请求体日志
     *
     * @param request 请求
     * @param body    请求体
     */
    private void traceRequest(HttpRequest request, byte[] body) {
        String requestBody = new String(body, StandardCharsets.UTF_8);
        log.info("===============================Request Begin===============================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders());
        log.info("Request body: {}", requestBody);
        log.info("===============================Request End=================================");
    }

    /**
     * 跟踪响应
     * <p>
     * ContentType为JSON格式则打印JSON格式的响应体日志
     *
     * @param response 响应
     * @throws IOException ioexception
     */
    private void traceResponse(ClientHttpResponse response) throws IOException {
        String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
        log.info("===============================Response Begin==============================");
        log.info("Status code  : {}", response.getStatusCode());
        log.info("Status text  : {}", response.getStatusText());
        log.info("Headers      : {}", response.getHeaders());
        log.info("Response body: {}", responseBody);
        log.info("===============================Response End================================");
    }


    /**
     * 判断本次请求的数据类型是否为json
     *
     * @param contentType 内容类型
     * @return boolean
     */
    private boolean contentTypeIsJson(String contentType) {
        if (StringUtils.isBlank(contentType)) {
            return false;
        }
        return contentType.contains(MediaType.APPLICATION_JSON_VALUE);
    }
}
