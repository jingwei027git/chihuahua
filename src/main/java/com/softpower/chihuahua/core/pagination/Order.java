package com.softpower.chihuahua.core.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.softpower.chihuahua.core.enums.SortOption;

@Getter
@Setter
@AllArgsConstructor
public class Order {
	private String name;
	private SortOption option;
}
