/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferayinaction.portlet.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferayinaction.portlet.model.Book;
import com.liferayinaction.portlet.service.BookLocalServiceUtil;

/**
 * @author augello
 * @generated
 */
public abstract class BookActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public BookActionableDynamicQuery() throws SystemException {
		setBaseLocalService(BookLocalServiceUtil.getService());
		setClass(Book.class);

		setClassLoader(com.liferayinaction.portlet.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("bookId");
	}
}