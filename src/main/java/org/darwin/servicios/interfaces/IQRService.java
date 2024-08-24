package org.darwin.servicios.interfaces;

public interface IQRService {
    byte[] generateQRCode(String text, int width, int height);
}
