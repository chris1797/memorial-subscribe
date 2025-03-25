package memorial.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    // ThreadPoolTaskExecutor: 스레드 풀을 사용하여 스레드를 관리하므로, 스레드 생성 및 삭제에 따른 오버헤드가 발생하지 않음
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 기본 스레드 수
        executor.setMaxPoolSize(50); // 최대 스레드 수
        executor.setQueueCapacity(50); // 대기열, 기본값은 무한
        executor.setThreadNamePrefix("Async FileExecutor-"); // 스레드 prefix naming 설정
        executor.initialize();

        return executor;
    }
}
