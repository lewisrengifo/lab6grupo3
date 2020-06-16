package sw2.lab6.teletok.entity;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

@Component
public class StorageService {
    //Coloca Aqui la carpeta donde se guardara las imagenes
    String fileLocation="C:/Users/Eduardo/Desktop/EduardoImportante/SW/fotos/";
    public  HashMap<String,String> store(MultipartFile file){
        HashMap<String,String> map = new HashMap<>();
        String filename= StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                map.put("estado", "error");
                map.put("msg", "No se puede guardar vacio");
            } else if (filename.contains("..")) {
                map.put("estado", "error");
                map.put("msg", "No se permite '..' ");
            } else {
                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = Paths.get(fileLocation);
                    Files.copy(inputStream, filePath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
                    map.put("estado", "exito");
                    map.put("fileName", filename);
                } catch (IOException e) {
                    System.out.println("aaa");
                }
            }
        } finally {
            System.out.println("aaa");
        }
        return map;
    }
}
