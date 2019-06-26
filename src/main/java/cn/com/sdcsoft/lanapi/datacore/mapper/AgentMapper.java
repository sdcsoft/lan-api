package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.Agent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AgentMapper {
    @Select("select * from Agent")
    @ResultType(Agent.class)
    List<Agent> findAll();

    @Select("select * from Agent where id=#{id}")
    Agent findOneById(@Param("id") int id);

    @Select("select * from Agent where AgentName like CONCAT('%',#{agentName},'%')")
    List<Agent> find(@Param("agentName") String agentName);

    @Update("update Agent set AgentName=#{agentName},Status=#{status} where Id = #{id}")
    void modifyAgent(@Param("id") int id, @Param("agentName") String agentName, @Param("status") int status);

    @Select("select count(AgentName) from Agent where AgentName=#{agentName}")
    int checkAgent(@Param("agentName") String agentName);

    @Insert("insert into Agent (AgentName,Status) values (#{agentName},#{status})")
    void addAgent(@Param("agentName") String agentName, @Param("status") int status);


}