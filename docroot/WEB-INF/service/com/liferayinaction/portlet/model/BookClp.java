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

package com.liferayinaction.portlet.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferayinaction.portlet.service.BookLocalServiceUtil;
import com.liferayinaction.portlet.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author augello
 */
public class BookClp extends BaseModelImpl<Book> implements Book {
	public BookClp() {
	}

	public Class<?> getModelClass() {
		return Book.class;
	}

	public String getModelClassName() {
		return Book.class.getName();
	}

	public long getPrimaryKey() {
		return _bookId;
	}

	public void setPrimaryKey(long primaryKey) {
		setBookId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_bookId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("bookId", getBookId());
		attributes.put("title", getTitle());
		attributes.put("isbn", getIsbn());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long bookId = (Long)attributes.get("bookId");

		if (bookId != null) {
			setBookId(bookId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String isbn = (String)attributes.get("isbn");

		if (isbn != null) {
			setIsbn(isbn);
		}
	}

	public long getBookId() {
		return _bookId;
	}

	public void setBookId(long bookId) {
		_bookId = bookId;

		if (_bookRemoteModel != null) {
			try {
				Class<?> clazz = _bookRemoteModel.getClass();

				Method method = clazz.getMethod("setBookId", long.class);

				method.invoke(_bookRemoteModel, bookId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;

		if (_bookRemoteModel != null) {
			try {
				Class<?> clazz = _bookRemoteModel.getClass();

				Method method = clazz.getMethod("setTitle", String.class);

				method.invoke(_bookRemoteModel, title);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getIsbn() {
		return _isbn;
	}

	public void setIsbn(String isbn) {
		_isbn = isbn;

		if (_bookRemoteModel != null) {
			try {
				Class<?> clazz = _bookRemoteModel.getClass();

				Method method = clazz.getMethod("setIsbn", String.class);

				method.invoke(_bookRemoteModel, isbn);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getBookRemoteModel() {
		return _bookRemoteModel;
	}

	public void setBookRemoteModel(BaseModel<?> bookRemoteModel) {
		_bookRemoteModel = bookRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _bookRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_bookRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			BookLocalServiceUtil.addBook(this);
		}
		else {
			BookLocalServiceUtil.updateBook(this);
		}
	}

	@Override
	public Book toEscapedModel() {
		return (Book)ProxyUtil.newProxyInstance(Book.class.getClassLoader(),
			new Class[] { Book.class }, new AutoEscapeBeanHandler(this));
	}

	public Book toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		BookClp clone = new BookClp();

		clone.setBookId(getBookId());
		clone.setTitle(getTitle());
		clone.setIsbn(getIsbn());

		return clone;
	}

	public int compareTo(Book book) {
		long primaryKey = book.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BookClp)) {
			return false;
		}

		BookClp book = (BookClp)obj;

		long primaryKey = book.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{bookId=");
		sb.append(getBookId());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", isbn=");
		sb.append(getIsbn());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.liferayinaction.portlet.model.Book");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>bookId</column-name><column-value><![CDATA[");
		sb.append(getBookId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isbn</column-name><column-value><![CDATA[");
		sb.append(getIsbn());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _bookId;
	private String _title;
	private String _isbn;
	private BaseModel<?> _bookRemoteModel;
}