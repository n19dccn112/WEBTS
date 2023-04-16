package com.nhom10.webts.model;

import com.nhom10.webts.model.entity.Mon;
import com.nhom10.webts.model.entity.TonKho;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NguyenLieuId implements Serializable {
    @ManyToOne
    private TonKho tonKho;
    @ManyToOne
    private Mon mon;
}
