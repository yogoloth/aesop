/*
 * Copyright 2012-2015, the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aesop.serializer.batch.reader;

import org.aesop.serializer.model.UserInfo;

/**
 * User Info service search result object
 * @author Regunath B
 *
 */
public class SearchResult {
	int total;
	UserInfo[] results;
	public SearchResult() {			
	}
	public SearchResult(int total, UserInfo[] results) {
		super();
		this.total = total;
		this.results = results;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public UserInfo[] getResults() {
		return results;
	}
	public void setResults(UserInfo[] results) {
		this.results = results;
	}
}
