/*
 *
 *  Copyright 2016-2017 Red Hat, Inc, and individual contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package io.openshift.booster;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {

    private final String content;
    private Date date;

	public Greeting() {
        this.content = null;
    }

    public Greeting(String content, Date date) {
        this.content = content;
        this.date = date;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("date")
    public Date getDate() {
		return date;
	}
}
