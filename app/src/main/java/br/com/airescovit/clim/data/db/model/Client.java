package br.com.airescovit.clim.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Logics on 03/02/2018.
 */

@Entity
public class Client {

    @Id
    private Long id;

    private Long addressId;

    private String name;

    private String phone;

    @ToOne(joinProperty = "addressId")
    private Address address;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 883866064)
    private transient ClientDao myDao;

    @Generated(hash = 295022261)
    public Client(Long id, Long addressId, String name, String phone) {
        this.id = id;
        this.addressId = addressId;
        this.name = name;
        this.phone = phone;
    }

    @Generated(hash = 1485887936)
    public Client() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Generated(hash = 1156467801)
    private transient Long address__resolvedKey;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 489389972)
    public Address getAddress() {
        Long __key = this.addressId;
        if (address__resolvedKey == null || !address__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AddressDao targetDao = daoSession.getAddressDao();
            Address addressNew = targetDao.load(__key);
            synchronized (this) {
                address = addressNew;
                address__resolvedKey = __key;
            }
        }
        return address;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 607080948)
    public void setAddress(Address address) {
        synchronized (this) {
            this.address = address;
            addressId = address == null ? null : address.getId();
            address__resolvedKey = addressId;
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


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void attachEntities() {
        setAddress(address);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2049572258)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getClientDao() : null;
    }
}
