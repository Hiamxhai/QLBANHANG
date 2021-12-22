package communityuni.com.model

class SanPham {
    var ma : String = " "
    var ten : String = " "
    var gia : Double = 0.0

    constructor()
    constructor(ma : String, ten : String, gia : Double)  {
        this.ma = ma
        this.ten = ten
        this.gia = gia

    }

}