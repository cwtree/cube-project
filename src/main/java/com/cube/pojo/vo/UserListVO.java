package com.cube.pojo.vo;

import java.util.List;

import com.cube.pojo.Page;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月10日
 */
@Getter
@Setter
@ToString
@Builder
public class UserListVO {

	private Page page;
	private List<UserVO> list;

}
