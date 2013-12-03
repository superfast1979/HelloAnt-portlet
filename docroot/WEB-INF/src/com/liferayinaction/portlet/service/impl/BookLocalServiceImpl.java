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

package com.liferayinaction.portlet.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferayinaction.portlet.model.Book;
import com.liferayinaction.portlet.service.base.BookLocalServiceBaseImpl;
import com.liferayinaction.portlet.service.persistence.BookUtil;

/**
 * The implementation of the book local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferayinaction.portlet.service.BookLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author augello
 * @see com.liferayinaction.portlet.service.base.BookLocalServiceBaseImpl
 * @see com.liferayinaction.portlet.service.BookLocalServiceUtil
 */
public class BookLocalServiceImpl extends BookLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferayinaction.portlet.service.BookLocalServiceUtil} to access the book local service.
	 */
    public Book addBook(String title, String isbn) throws SystemException {
        Book l = BookUtil.create(CounterLocalServiceUtil.increment());
        l.setIsbn(isbn);
        l.setTitle(title);

        return BookUtil.update(l, true);
    }

    public java.util.List<Book> getAllBooks() throws SystemException {
        return BookUtil.findAll();
    }
}