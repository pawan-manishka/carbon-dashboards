/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.dashboards.metadata.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.dashboards.metadata.exception.MetadataException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Class to represent a Metadata(Dashboard JSON).
 */
public class Metadata {

    private static final Logger log = LoggerFactory.getLogger(Metadata.class);

    protected String id;
    protected String url;
    protected String name;
    protected String version;
    protected String description;
    protected String owner;
    protected String lastUpdatedBy;
    protected long createdTime;
    protected long lastUpdatedTime;
    protected boolean isShared;

    protected String parentId;
    protected Object content;

    /**
     * This method is used to get url of the dashboard
     *
     * @return String returns dashboard url
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method is used to set the url of dashboard
     *
     * @param url url of the dashboard
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method is used get the name of the metadata/dashboard
     *
     * @return String returns metadata/dashboard name
     */
    public String getName() {
        return name;
    }

    /**
     * This method is used to set the name of metadata/dashboard
     *
     * @param name the metadata/dashboard name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is used to get the version of the metadata/dashboard
     *
     * @return String returns the metadata/dashboard version
     */
    public String getVersion() {
        return version;
    }

    /**
     * This method is used to set the version of metadata/dashboard
     *
     * @param version version of the metadata/dashboard
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * This methos is used to get the description of the metadata/dashboard
     *
     * @return String return the description of metadata/dashboard
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method is used to set the description of the metadata/dashboard
     *
     * @param description description of the metadata/dashboard
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method is used to get the owner of the metadata/dashboard
     *
     * @return String return the owner of the metadata/dashboard
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method is used to set the owner of the dashboard/metadata
     *
     * @param owner owner of the dashboard
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method is used to get the user who updated the metadata/dashboard lastly
     *
     * @return String returns user who updated the metadata/dashboard lastly
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method is used to set the user who updated the metadata/dashboard lastly
     *
     * @param lastUpdatedBy user who updated the metadata/dashboard lastly
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method is to get creation time of metadata/dashboard
     *
     * @return long returns the metadata/dashboard - created time
     */
    public long getCreatedTime() {
        return createdTime;
    }

    /**
     * This method is used to set the creation time of metadata/dashboard
     *
     * @param createdTime creation time of metadata/dashboard
     */
    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method is used to get the last updated time of metadata/dashboard
     *
     * @return long returns last updated time of metadata/dashboard
     */
    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    /**
     * This method is used to set the last updated time of metadata/dashboard
     *
     * @param lastUpdatedTime last updated time of metadata/dashboard
     */
    public void setLastUpdatedTime(long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    /**
     * This method is used to get the metadata shared state
     *
     * @return boolean returns true if the metadata/dashboard is shared
     */
    public boolean isShared() {
        return isShared;
    }

    /**
     * This method is used to set the boolean according to metadata shared state
     *
     * @param shared true if the metadata/dashboard is shared
     */
    public void setShared(boolean shared) {
        isShared = shared;
    }

    /**
     * This method is used to get the parent id of the dashboard/metadata
     *
     * @return String returns the parentID of the metadata/dashboard
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method is used to set the parent id of the dashboard/metadata
     *
     * @param parentId parentId of the metadata/dashboard
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * This method is used to get the DB level dashboard id
     *
     * @return String returns the id of metadata/dashboard
     */
    public String getId() {
        return id;
    }

    /**
     * This method is used to set the DB level dashboard id
     *
     * @param id id of metadata/dashboard
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method is to get the content/dashbord json
     *
     * @return Object returns the content of dashboard
     */
    public Object getContent() throws MetadataException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader((InputStream) content, Charset.defaultCharset()));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new MetadataException("Error in retrieving dashboard content", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("Error in closing dashboard content buffered reader");
                }
            }
        }
        return sb.toString();
    }

    /**
     * This method is used to set the content/dashboard json
     *
     * @param content content of dashboard
     */
    public void setContent(Object content) {
        this.content = content;
    }

    /**
     * This method is used to get the dashboard json as a inputstream
     *
     * @return InputStream returns the content as a inputstream
     * @throws MetadataException
     */
    public InputStream getContentStream() throws MetadataException {

        if (content == null) {
            throw new MetadataException("Resource content is empty.");
        }
        if (content instanceof byte[]) {
            return new ByteArrayInputStream((byte[]) content);
        } else if (content instanceof String) {
            byte[] contentBytes = ((String) content).getBytes(Charset.forName("UTF-8"));
            return new ByteArrayInputStream(contentBytes);
        } else if (content instanceof InputStream) {
            return (InputStream) content;
        } else {
            throw new MetadataException("Resource content is empty.");
        }
    }
}
