
 
/**
 * Classe que lê e escreve em um arquivo. Utiliza a java.io.RandomAccessFile
 * porem com essa classe os caracteres escritos não sobrescrevem os caracteres
 * posteriores (efeito insert).
 * 
 * 
 * @author Vânio Meurer
 * @since 09/11/2009 16:01:13
 * @version 1.0.0
 */
package br.ufjf.pgcc.plscience.util;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 *
 * @author Fran
 */

public class RandomAccessFilePlus implements DataOutput, DataInput {
 
	private RandomAccessFile raf;
	private String lineSeparator;
 
	/**
	 * Construtor padrão.
	 * 
	 * @param raf
	 *            Utilize uma RandomAccessFile com acesso a escrita ("rw") caso
	 *            desejar escrever.
	 */
	public RandomAccessFilePlus(RandomAccessFile raf) {
		this.raf = raf;
		lineSeparator = (String) java.security.AccessController
				.doPrivileged(new sun.security.action.GetPropertyAction(
						"line.separator"));
	}
 
	/**
	 * Quebra de linha no arquivo.
	 * 
	 * @throws IOException
	 */
	public void newLine() throws IOException {
		writeBytes(lineSeparator);
	}
 
	public void write(int arg0) throws IOException {
		int length = String.valueOf(arg0).length();
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(length, filePointer);
		raf.write(arg0);
		backToPosition(filePointer + length, b);
	}
 
	public void write(byte[] arg0) throws IOException {
		int length = arg0.length;
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(length, filePointer);
		raf.write(arg0);
		backToPosition(filePointer + length, b);
	}
 
	public void write(byte[] arg0, int arg1, int arg2) throws IOException {
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(arg2, filePointer);
		raf.write(arg0, arg1, arg2);
		backToPosition(filePointer + arg2, b);
	}
 
	public void writeBoolean(boolean arg0) throws IOException {
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(1, filePointer);
		raf.writeBoolean(arg0);
		backToPosition(filePointer + 1, b);
	}
 
	public void writeByte(int arg0) throws IOException {
		int length = String.valueOf(arg0).length();
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(length, filePointer);
		raf.writeByte(arg0);
		backToPosition(filePointer + length, b);
	}
 
	public void writeBytes(String arg0) throws IOException {
		int length = arg0.length();
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(length, filePointer);
		raf.writeBytes(arg0);
		backToPosition(filePointer + length, b);
	}
 
	public void writeChar(int arg0) throws IOException {
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(2, filePointer);
		raf.writeChar(arg0);
		backToPosition(filePointer + 2, b);
	}
 
	public void writeChars(String arg0) throws IOException {
		int length = arg0.length() << 1;
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(length, filePointer);
		raf.writeChars(arg0);
		backToPosition(filePointer + length, b);
	}
 
	public void writeDouble(double arg0) throws IOException {
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(8, filePointer);
		raf.writeDouble(arg0);
		backToPosition(filePointer + 8, b);
	}
 
	public void writeFloat(float arg0) throws IOException {
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(4, filePointer);
		raf.writeFloat(arg0);
		backToPosition(filePointer + 4, b);
	}
 
	public void writeInt(int arg0) throws IOException {
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(4, filePointer);
		raf.writeInt(arg0);
		backToPosition(filePointer + 4, b);
	}
 
	public void writeLong(long arg0) throws IOException {
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(8, filePointer);
		raf.writeLong(arg0);
		backToPosition(filePointer + 8, b);
	}
 
	public void writeShort(int arg0) throws IOException {
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(2, filePointer);
		raf.writeShort(arg0);
		backToPosition(filePointer + 2, b);
	}
 
	public void writeUTF(String arg0) throws IOException {
		int length = arg0.length();
		long filePointer = raf.getFilePointer();
		byte[] b = increaseFile(length, filePointer);
		raf.writeUTF(arg0);
		raf.write(b);
	}
 
	private byte[] increaseFile(int length, long filePointer)
			throws IOException {
		byte[] b = new byte[(int) (raf.length() - filePointer)];
		raf.readFully(b);
		raf.setLength(raf.length() + length);
		raf.seek(filePointer);
		return b;
	}
 
	private void backToPosition(long position, byte[] b) throws IOException {
		raf.write(b);
		raf.seek(position);
	}
 
	public boolean readBoolean() throws IOException {
		return raf.readBoolean();
	}
 
	public byte readByte() throws IOException {
		return raf.readByte();
	}
 
	public char readChar() throws IOException {
		return raf.readChar();
	}
 
	public double readDouble() throws IOException {
		return raf.readDouble();
	}
 
	public float readFloat() throws IOException {
		return raf.readFloat();
	}
 
	public void readFully(byte[] arg0) throws IOException {
		raf.readFully(arg0);
	}
 
	public void readFully(byte[] arg0, int arg1, int arg2) throws IOException {
		raf.readFully(arg0, arg1, arg2);
	}
 
	public int readInt() throws IOException {
		return raf.readInt();
	}
 
	public String readLine() throws IOException {
		return raf.readLine();
	}
 
	public long readLong() throws IOException {
		return raf.readLong();
	}
 
	public short readShort() throws IOException {
		return raf.readShort();
	}
 
	public String readUTF() throws IOException {
		return raf.readUTF();
	}
 
	public int readUnsignedByte() throws IOException {
		return raf.readUnsignedByte();
	}
 
	public int readUnsignedShort() throws IOException {
		return raf.readUnsignedShort();
	}
 
	public int skipBytes(int arg0) throws IOException {
		return raf.skipBytes(arg0);
	}
 
	public void close() throws IOException {
		raf.close();
	}
 
	public void seek(long pos) throws IOException {
		raf.seek(pos);
	}
 
	/**
	 * Getter RandomAccessFile
	 * 
	 * @return
	 */
	public RandomAccessFile getRAF() {
		return raf;
	}
 
	/**
	 * Setter RandomAccessFile
	 * 
	 * @param raf
	 */
	public void setRAF(RandomAccessFile raf) {
		this.raf = raf;
        }
}