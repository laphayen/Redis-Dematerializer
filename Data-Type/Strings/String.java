try (var jedisPool = new JedisPool("localhost", 6379)) {
    try (Jedis jedis = jedisPool.getResource()) {
        jedis.set("users:300:email", "laphayen@gmail.com");
        jedis.set("users:300:name", "laphayen");
        jedis.set("users:300:age", "100");

        var userEmail = jedis.get("users:300:email");
        System.out.println(userEmail);

        List<String> userInfo =  jedis.mget("users:300:email", "users:300:name", "users:300:age");
        userInfo.forEach(System.out::println);

        long counter = jedis.incr("counter");
        System.out.println(counter);

        counter = jedis.incrBy("counter", 10L);
        System.out.println(counter);

        counter = jedis.decr("counter");
        System.out.println(counter);

        counter = jedis.decrBy("counter", 20L);
        System.out.println(counter);
    }

}