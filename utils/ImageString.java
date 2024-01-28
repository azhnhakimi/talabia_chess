package utils;

import java.io.File;

import model.pieces.Piece;

public class ImageString {
    
    public static String getImagePath(Piece piece){
        String pieceType = piece.getPieceType();
        String casedPieceType = Character.toUpperCase(pieceType.charAt(0)) + pieceType.substring(1);
        String filePath = "assets" + File.separator + piece.getColour() + casedPieceType + ".png";
        return filePath;
    }
    
}
