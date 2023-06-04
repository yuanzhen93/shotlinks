package com.gupao.shotlink.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.shotlink.repository.UrlMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UrlMappingMapper extends BaseMapper<UrlMapping> {

    @Update("update url_mapping set interview_count = interview_count+1, expired_date = NOW() where short_url = #{shortUrl}")
    public void updateRecord(String shortUrl);

}
