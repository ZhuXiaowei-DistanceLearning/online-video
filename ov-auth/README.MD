# 认证与授权

@Author:zxw

@email:502513206@qq.com

> 参考<<重新定义springcloud>>

------

# 1.前言

通过之前的shiro框架的学习，对于系统权限访问这块的重点就是认证与授权。所谓认证就是判断当前请求用户是否为系统角色，授权即认证通过后基于用户哪些功能权限。

这个实现起来并不难，对于单一的系统直接使用filter即可完成。

传统的SSO单点登录每次都到服务中进行一次认证，但服务进行划分拆解后，每个服务都进行授权认证会带来非常大的性能损耗，对于此的解决方案有几种。

单体应用

![image-20200718204745190](D:\code\IDEA CODE\online-video\ov-auth\README.assets\image-20200718204745190.png)

传统sso

![image-20200718205111200](D:\code\IDEA CODE\online-video\ov-auth\README.assets\image-20200718205111200.png)

1. 分布式session与网关结合

   使用缓存中间件缓存sessionId,不过需要保证中间件的高可用性

2. 客户端Token与网关结合

   客户端携带token到网关层进行校验和认证

3. Cookie结合

# 2.JWT

JSONWEBTOKEN全称

主要有三部分组成

Header：根据官方的定义这是一个json,在接口中定义了一些默认值

`Header.java`

```java
/** JWT {@code Type} (typ) value: <code>"JWT"</code> */
    public static final String JWT_TYPE = "JWT";

    /** JWT {@code Type} header parameter name: <code>"typ"</code> */
    public static final String TYPE = "typ";

    /** JWT {@code Content Type} header parameter name: <code>"cty"</code> */
    public static final String CONTENT_TYPE = "cty";

    /** JWT {@code Compression Algorithm} header parameter name: <code>"zip"</code> */
    public static final String COMPRESSION_ALGORITHM = "zip";

    /** JJWT legacy/deprecated compression algorithm header parameter name: <code>"calg"</code>
     * @deprecated use {@link #COMPRESSION_ALGORITHM} instead. */
    @Deprecated
    public static final String DEPRECATED_COMPRESSION_ALGORITHM = "calg";
```

`JwsHeader.java`

```java
/** JWS {@code Algorithm} header parameter name: <code>"alg"</code> */
public static final String ALGORITHM = "alg";

/** JWS {@code JWT Set URL} header parameter name: <code>"jku"</code> */
public static final String JWK_SET_URL = "jku";

/** JWS {@code JSON Web Key} header parameter name: <code>"jwk"</code> */
public static final String JSON_WEB_KEY = "jwk";

/** JWS {@code Key ID} header parameter name: <code>"kid"</code> */
public static final String KEY_ID = "kid";

/** JWS {@code X.509 URL} header parameter name: <code>"x5u"</code> */
public static final String X509_URL = "x5u";

/** JWS {@code X.509 Certificate Chain} header parameter name: <code>"x5c"</code> */
public static final String X509_CERT_CHAIN = "x5c";

/** JWS {@code X.509 Certificate SHA-1 Thumbprint} header parameter name: <code>"x5t"</code> */
public static final String X509_CERT_SHA1_THUMBPRINT = "x5t";

/** JWS {@code X.509 Certificate SHA-256 Thumbprint} header parameter name: <code>"x5t#S256"</code> */
public static final String X509_CERT_SHA256_THUMBPRINT = "x5t#S256";

/** JWS {@code Critical} header parameter name: <code>"crit"</code> */
public static final String CRITICAL = "crit";
```

想选择使用的签名算法可以在`SignatureAlgorithm.java`类中查看

pyload:存放用户信息，主要有三类

- 标准中注册的声明
- 公共的声明
- 私有的声明

**标准中注册的声明** (建议但不强制使用) ：

- **iss**: jwt签发者
- **sub**: jwt所面向的用户
- **aud**: 接收jwt的一方
- **exp**: jwt的过期时间，这个过期时间必须要大于签发时间
- **nbf**: 定义在什么时间之前，该jwt都是不可用的.
- **iat**: jwt的签发时间
- **jti**: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。

**公共的声明** ：
 公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，因为该部分在客户端可解密.

**私有的声明** ：
 私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。

signature:第三部分是一个签证信息，对header和payload中的加密进行盐加密组合

