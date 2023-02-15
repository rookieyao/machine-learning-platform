package com.pzj.project.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisConfig
 * @Description
 * @Author yaoqi
 * @Date 2022/7/7 18:36
 * @Version 1.0
 **/
@Component
@Slf4j
public class RedisConfig {

    /**
     * redis host
     */
    @Value("${spring.redis.host}")
    private String hostName;
    /**
     * redis端口
     */
    @Value("${spring.redis.port}")
    private int port;
    /**
     * redis密码
     */
    @Value("${spring.redis.password}")
    private String password;
    /**
     * 最大连接数
     */
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdl;
    /**
     * 最小空闲连接数
     */
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdl;
    /**
     * 超时时间
     */
    @Value("${spring.redis.timeout}")
    private int timeout;
    /**
     * 配置需要操作的db
     */
    @Value("#{'${spring.redis.dbs}'.split(',')}")
    private List<Integer> dbs;
    /**
     * 默认的db
     */
    private int defaultDb=0;
    /**
     * 存储redisTemplate
     */
    private static Map<Integer, StringRedisTemplate> redisTemplateMap = new HashMap<>();

    /**
     * 初始化dbs各自的redisTemplate
     *
     * @author wrx
     * @date 2022/3/8 10:37
     */
    @PostConstruct
    public void initRedisTemp() throws Exception {
        log.info("###### START 初始化 Redis 连接池 START ######");
        defaultDb = dbs.get(0);
        for (Integer db : dbs) {
            log.info("###### 正在加载Redis-db-" + db + " ######");
            redisTemplateMap.put(db, redisTemplateObject(db));
        }
        log.info("###### END 初始化 Redis 连接池 END ######");
    }

    /**
     * 实例化redisTemplate
     *
     * @param dbIndex 指定redis数据库
     * @return StringRedisTemplate
     * @author wrx
     * @date 2022/3/8 10:33
     */
    private StringRedisTemplate redisTemplateObject(Integer dbIndex) throws Exception {
        StringRedisTemplate redisTemplateObject = new StringRedisTemplate();
        redisTemplateObject.setConnectionFactory(redisConnectionFactory(jedisPoolConfig(), dbIndex));
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    /**
     * 连接池配置信息
     *
     * @return JedisPoolConfig
     * @author wrx
     * @date 2022/3/8 10:36
     */
    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdl);
        poolConfig.setMinIdle(minIdl);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        // 当池内没有可用的连接时，最大等待时间
        poolConfig.setMaxWaitMillis(10000);
        // ------其他属性根据需要自行添加-------------
        return poolConfig;
    }

    /**
     * jedis连接工厂
     *
     * @param jedisPoolConfig jedis池配置
     * @return db 指定redis数据库
     * @author wrx
     * @date 2022/3/8 10:35
     */
    private RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig, int db) {
        // 单机版jedis
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        // 设置redis服务器的host或者ip地址
        redisStandaloneConfiguration.setHostName(hostName);
        // 设置默认使用的数据库
        redisStandaloneConfiguration.setDatabase(db);
        // 设置密码
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        // 设置redis的服务的端口号
        redisStandaloneConfiguration.setPort(port);

        // 获得默认的连接池构造器
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        // 指定jedisPoolConifig来修改默认的连接池构造器
        jpcb.poolConfig(jedisPoolConfig);
        // 通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        // 单机配置 + 客户端配置 = jedis连接工厂
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    /**
     * 获取指定的db的template
     *
     * @param db 指定redis库
     * @return StringRedisTemplate
     * @author wrx
     * @date 2022/3/8 10:03
     */
    public StringRedisTemplate getRedisTemplateByDb(int db) {
        return redisTemplateMap.get(db);
    }

    /**
     * 获取默认的template
     *
     * @return StringRedisTemplate
     * @author wrx
     * @date 2022/3/8 10:29
     */
    public StringRedisTemplate getRedisTemplate() {
        return redisTemplateMap.get(defaultDb);
    }
}
