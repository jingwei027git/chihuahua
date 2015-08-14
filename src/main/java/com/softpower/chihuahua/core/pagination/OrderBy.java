package com.softpower.chihuahua.core.pagination;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.softpower.chihuahua.core.enums.SortOption;

import lombok.Getter;

@SuppressWarnings("serial")
public class OrderBy implements Serializable {

	@Getter
	private List<Order> orders = Lists.newArrayList();

	public static OrderBy DEFAULT = new OrderBy() {
		{
			add(new Order("id", SortOption.ASC));
		}
	};

	public static OrderBy create(String field, SortOption sort) {
		OrderBy orderBy = new OrderBy();
		orderBy.add(new Order(field, sort));
		return orderBy;
	}

	public void add(Order order) {
		getOrders().add(order);
	}

	public void clear() {
		getOrders().clear();
	}

}
