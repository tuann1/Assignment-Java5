package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "ChiTietSP")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ChiTietSanPham {

    @Id
    @Column(name = "Id", columnDefinition = "UNIQUEIDENTIFIER")
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "IdSP")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "IdNsx")
    private NSX nsx;

    @ManyToOne
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "IdDongSP")
    private DongSanPham dongSanPham;

    @Column(name = "NamBH")
    @PositiveOrZero(message = "Năm bảo hành phải là số nguyên dương.")
    private Integer namBaoHanh;

    @Column(name = "MoTa")
    @NotBlank(message = "Vui lòng không bỏ trống.")
    private String moTa;

    @Column(name = "SoLuongTon")
    @PositiveOrZero(message = "Vui lòng nhập số nguyên dương và lớn hơn 0.")
    private Integer soLuongTon;

    @Column(name = "GiaNhap")
    @PositiveOrZero(message = "Vui lòng nhập số nguyên dương và lớn hơn 0.")
    private Long giaNhap;

    @Column(name = "GiaBan")
    @PositiveOrZero(message = "Vui lòng nhập số nguyên dương và lớn hơn 0.")
    private Long giaBan;

}
