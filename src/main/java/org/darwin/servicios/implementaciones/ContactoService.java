package org.darwin.servicios.implementaciones;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.darwin.modelos.Contacto;
import org.darwin.repositorios.IContactoRepository;
import org.darwin.servicios.interfaces.IContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ContactoService implements IContactoService {
    @Autowired
    private IContactoRepository contactoRepository;

    @Value("${qr.storage.path}")
    private String qrStoragePath;

    @Override
    public Page<Contacto> buscarTodosPaginados(Pageable pageable) {
        return contactoRepository.findAll(pageable);
    }

    @Override
    public List<Contacto> obtenerTodos() {
        return contactoRepository.findAll();
    }

    @Override
    public Optional<Contacto> buscarPorId(Integer id) {
        return contactoRepository.findById(id);
    }


    @Override
    public Contacto crearOEditar(Contacto contacto) {
        Contacto savedContacto = contactoRepository.save(contacto);
        generateQRCode(savedContacto);
        return savedContacto;
    }

    private void generateQRCode(Contacto contacto) {
        try {
            String qrContent = String.format("Nombre: %s\nEmail: %s\nTeléfono: %s",
                    contacto.getNombre(), contacto.getEmail(), contacto.getNumero());

            String qrFileName = contacto.getId() + "_qr.png";  // Nombre del archivo QR
            Path uploadPath = Paths.get(qrStoragePath, qrFileName);

            BitMatrix matrix = new MultiFormatWriter().encode(qrContent, BarcodeFormat.QR_CODE, 350, 350);

            MatrixToImageWriter.writeToPath(matrix, "PNG", uploadPath);

            // Usar barras normales en la ruta
            contacto.setQrCodePath("/qr-codes/" + qrFileName);

            contactoRepository.save(contacto);  // Actualizar contacto con la ruta del QR

        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de excepciones apropiado
        }
    }

    public List<Contacto> findContactosEliminados() {
        return contactoRepository.findByEliminado(true);
    }

    @Override
    public void eliminarPorId(Integer id) {
        Contacto contacto = contactoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
        contacto.setEliminado(true);
        contactoRepository.save(contacto);
    }
    @Override
    public void restaurarContacto(Integer id) {
        Contacto contacto = contactoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
        contacto.setEliminado(false);
        contactoRepository.save(contacto);
    }


    @Override
    public void eliminarDefinitivamenteContacto(Integer id) {
        contactoRepository.deleteById(id);
    }

    @Override
    public Page<Contacto> buscarTodosNoEliminadosPaginados(Pageable pageable){
        return contactoRepository.findByEliminadoFalse(pageable);
    }
    @Override
    public Contacto obtenerContactoPorId(Integer id) {
        return contactoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
    }

}
