package com.contoso.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * An abstract base class that is inherited by other domain classes
 * in the order application context.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1030901618763859238L;
	
	@LastModifiedDate
    private Date lastModified;
    @CreatedDate
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "lastModified=" + lastModified +
                ", createdAt=" + createdAt +
                '}';
    }
}
