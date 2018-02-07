package br.com.airescovit.clim.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Murilo Aires on 07/02/2018.
 */

@Entity
public class Task {

    @Id
    private Long id;

    @Generated(hash = 226549777)
    public Task(Long id) {
        this.id = id;
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
}
