package communityuni.com.io

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import communityuni.com.model.SanPham
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception

class JsonFileFactory { // day du lieu vao o cung
    public fun LuuDuLieu(dsSp: MutableList<SanPham>,pair:String):Boolean {
        try {
            var gson = Gson()
            var file = FileWriter(pair)
            gson.toJson(dsSp,file)
            file.close()
            return true
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
        return false
    }
    public fun DocDuLieu(pair: String) : MutableList<SanPham> {
        var dsSp: MutableList<SanPham> = mutableListOf()
        try {
            var gson = Gson()
            var file = FileReader(pair)
           dsSp  = gson.fromJson(file,
               object :TypeToken<MutableList<SanPham>>(){}.type)
                file.close()
        }
        catch (ex: Exception) {
            ex.printStackTrace()

        }
        return  dsSp

    }
}