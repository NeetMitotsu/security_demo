package com.yuudati.securitydemo.dao;

import com.yuudati.securitydemo.pojo.SysUrl;
import com.yuudati.securitydemo.pojo.SysUrlExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUrlMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    long countByExample(SysUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    int deleteByExample(SysUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_url",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_url (url, description)",
        "values (#{url,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SysUrl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    int insertSelective(SysUrl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    List<SysUrl> selectByExample(SysUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, url, description",
        "from sys_url",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.yuudati.securitydemo.dao.SysUrlMapper.BaseResultMap")
    SysUrl selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysUrl record, @Param("example") SysUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysUrl record, @Param("example") SysUrlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysUrl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_url
     *
     * @mbg.generated
     */
    @Update({
        "update sys_url",
        "set url = #{url,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysUrl record);
}