package communityuni.com.test

import communityuni.com.ui.SanPhamUl
import javax.swing.JFrame


fun main() {
    var gui:JFrame = JFrame("Chương trình quản lí Sản Phẩm ")
    var spUl = SanPhamUl()

    gui.contentPane = spUl.pnMain
    spUl.createMenu(gui);
    gui.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    gui.setSize(550,550)
    gui.setLocationRelativeTo(null)
    gui.isVisible = true
}

