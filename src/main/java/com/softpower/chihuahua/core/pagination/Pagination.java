package com.softpower.chihuahua.core.pagination;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.session.RowBounds;

@SuppressWarnings("serial")
public class Pagination extends RowBounds implements Serializable {

	public static final int PAGE_DEFAULT = 1;
	public static final int SIZE_DEFAULT = 10;
	public static final int SIZE_MAXIUM = Integer.MAX_VALUE;

	public static Pagination ALL = new Pagination(PAGE_DEFAULT, SIZE_MAXIUM, OrderBy.DEFAULT);

	@Getter @Setter private int page;
	@Getter @Setter private int size;
	@Getter @Setter private OrderBy orderBy;
	@Getter @Setter private String sql;

	public Pagination() {
		this(PAGE_DEFAULT, SIZE_DEFAULT);
	}

	public Pagination(int page, int size) {
		this(page, size, new OrderBy());
	}

	public Pagination(int page, int size, OrderBy orderBy) {
		super(NO_ROW_OFFSET, NO_ROW_LIMIT);
		this.page = page;
		this.size = size;
		this.orderBy = orderBy;
	}

	public int getPageOffset() {
		return (page - 1) * size;
	}

}
