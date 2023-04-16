package com.nhom10.webts.model;


import com.nhom10.webts.model.entity.DonDatHang;
import com.nhom10.webts.model.entity.Mon;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChiTietDDHId  implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    private DonDatHang donDatHang;

    @ManyToOne(fetch = FetchType.EAGER)
    private Mon mon;
}
