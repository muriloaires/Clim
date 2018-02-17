package br.com.airescovit.clim.data.db.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

/**
 * Created by Murilo Aires on 07/02/2018.
 */

@Entity
public class Task {

    @Id
    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("start_at")
    private Date startAt;

    @SerializedName("finish_at")
    private Date finishAt;

    @SerializedName("service_fee")
    private Double serviceFee;

    @ToOne(joinProperty = "clientId")
    @SerializedName("client")
    private Client client;

    private Long clientId;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;

    @Generated(hash = 1636229693)
    private transient Long client__resolvedKey;

    @Generated(hash = 2137751279)
    public Task(Long id, String title, String description, Date startAt,
                Date finishAt, Double serviceFee, Long clientId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.serviceFee = serviceFee;
        this.clientId = clientId;
    }

    @Generated(hash = 733837707)
    public Task() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartAt() {
        return this.startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getFinishAt() {
        return this.finishAt;
    }

    public void setFinishAt(Date finishAt) {
        this.finishAt = finishAt;
    }

    public Double getServiceFee() {
        return this.serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getClientId() {
        return this.clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 379745032)
    public Client getClient() {
        Long __key = this.clientId;
        if (client__resolvedKey == null || !client__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ClientDao targetDao = daoSession.getClientDao();
            Client clientNew = targetDao.load(__key);
            synchronized (this) {
                client = clientNew;
                client__resolvedKey = __key;
            }
        }
        return client;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1756624635)
    public void setClient(Client client) {
        synchronized (this) {
            this.client = client;
            clientId = client == null ? null : client.getId();
            client__resolvedKey = clientId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public void attachEntities() {
        setClient(client);
        client.attachEntities();
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }
}
