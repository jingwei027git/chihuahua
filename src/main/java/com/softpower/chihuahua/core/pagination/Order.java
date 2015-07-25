package com.softpower.chihuahua.core.pagination;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.softpower.chihuahua.core.enums.SortOption;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
public class Order implements Serializable {
	private String name;
	private SortOption option;
}
