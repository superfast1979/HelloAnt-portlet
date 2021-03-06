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

package com.liferayinaction.portlet.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferayinaction.portlet.model.Book;

import java.io.Serializable;

/**
 * The cache model class for representing Book in entity cache.
 *
 * @author augello
 * @see Book
 * @generated
 */
public class BookCacheModel implements CacheModel<Book>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{bookId=");
		sb.append(bookId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", isbn=");
		sb.append(isbn);
		sb.append("}");

		return sb.toString();
	}

	public Book toEntityModel() {
		BookImpl bookImpl = new BookImpl();

		bookImpl.setBookId(bookId);

		if (title == null) {
			bookImpl.setTitle(StringPool.BLANK);
		}
		else {
			bookImpl.setTitle(title);
		}

		if (isbn == null) {
			bookImpl.setIsbn(StringPool.BLANK);
		}
		else {
			bookImpl.setIsbn(isbn);
		}

		bookImpl.resetOriginalValues();

		return bookImpl;
	}

	public long bookId;
	public String title;
	public String isbn;
}