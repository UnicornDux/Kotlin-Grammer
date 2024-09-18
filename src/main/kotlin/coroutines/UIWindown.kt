package coroutines

import javax.swing.JFrame
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.ImageIcon
import java.awt.Dimension
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.Image

class MainWindow : JFrame() {
  private lateinit var button: JButton
  private lateinit var label: JLabel

  fun init() {
    button = JButton("点击获取 Logo")
    label = JLabel()
    label.size = Dimension(800, 600)

    contentPane.add(button, BorderLayout.NORTH)
    contentPane.add(label, BorderLayout.CENTER)
  }

  fun onButtonClick(listener: (ActionEvent) -> Unit) {
    button.addActionListener(listener)
  }

  fun setLogo(logoData: ByteArray) {
    val icon = ImageIcon(logoData)
    var image = icon.image
    image = image.getScaledInstance(label.width, label.height, Image.SCALE_SMOOTH)
    label.setIcon(ImageIcon(image))
  }
}
