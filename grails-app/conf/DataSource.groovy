dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            //url = "jdbc:h2:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            pooled = true
            driverClassName = "com.mysql.jdbc.Driver"
            username = "{REPLACE BY YOUR USERNAME}"
            password = "{REPLACE BY YOUR PASSWORD}"
            url = "jdbc:mysql://localhost/good_things?useUnicode=true&characterEncoding=utf-8&autoReconnect=true"  //&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull
            //Avoid mysql database interupt the connection
            properties {
               maxActive = 50
               maxIdle = 25
               minIdle = 5
               initialSize = 5
               minEvictableIdleTimeMillis = 1800000
               timeBetweenEvictionRunsMillis = 1800000
               maxWait = 10000
            }
         }
    }
}
