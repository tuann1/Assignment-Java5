package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "NSX")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class NSX {

    @Id
    @Column(name = "Id", columnDefinition = "UNIQUEIDENTIFIER")
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    private UUID id;

    @Column(name = "Ma")
    @NotBlank(message = "Không được để trống mã.")
    private String ma;

    @Column(name = "Ten")
    @NotBlank(message = "Không được để trống tên.")
    private String ten;

    @OneToMany(mappedBy = "nsx", fetch = FetchType.LAZY)
    List<ChiTietSanPham> list;

}
