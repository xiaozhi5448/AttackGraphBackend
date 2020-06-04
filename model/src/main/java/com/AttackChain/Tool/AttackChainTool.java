package com.AttackChain.Tool;
import com.loop0day.security.bean.DataFlow;
import com.loop0day.security.bean.Node;
import com.loop0day.security.conf.Config;
import com.loop0day.security.db.bean.*;
import com.loop0day.security.db.dao.*;

import com.loop0day.security.tool.MapTool;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AttackChainTool extends MapTool {
    /* 数据库会话对象 */
    private static SqlSession session = null;

    /*攻击模板对象*/
    private static AttackModelMapper attackModelMapper;
    /*攻击路径对象*/
    private static AttackPathMapper attackPathMapper;
    static {
        try {
            /* 数据库会话工厂对象 */
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(Config.MYBATIS_CONF_PATH));
            /* 初始化数据库会话对象 */
            AttackChainTool.session = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* 断言数据库会话对象不为空 */
        assert session != null;
        /* 攻击模板对象*/
        AttackChainTool.attackModelMapper = session.getMapper(AttackModelMapper.class);
        /* 攻击路径对象*/
        AttackChainTool.attackPathMapper = session.getMapper(AttackPathMapper.class);
    }

    /**
     * 修改数据库后提交
     */
    public static void commit() {
        AttackChainTool.session.commit();
    }

    /**
     * 关闭数据库会话
     */
    public static void close() {
        AttackChainTool.session.close();
    }

}
