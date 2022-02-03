package cubox.admin.cmmn.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Rotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

public class FileConvertToJsp {

	private static FileConvertToJsp instance;
	private static final Logger LOGGER = LoggerFactory.getLogger(FileConvertToJsp.class);
	private final int defaultWidth = 640; 			//저장될 최저 width 크기
	private final int defaultMaxQuality = 1000000;	//1M
	private final float defaultScale = 1f;
	private final float minScale = 0.5f;
	private boolean booQtyApply = false;
	//private final List<String> formatlst = Arrays.asList("bmp","wbmp","png","gif");

	static {
		instance = new FileConvertToJsp();
	}

	public static FileConvertToJsp getInstance() {
		return instance;
	}

	public byte[] imgConvertToJsp (byte[] imageByte) {
		List <String> fm = Arrays.asList("JPEG","JPG","jpeg","jpg");
		int orientation = 1; // 회전정보, 1. 0도, 3. 180도, 6. 270도, 8. 90도 회전한 정보
		byte[] jspByte = null;
		ByteArrayOutputStream baos = null;
		ImageOutputStream ios = null;
		ImageInputStream iis = null;
		BufferedImage bufferedImage = null;
		ByteArrayInputStream inputStream = null;
		ByteArrayInputStream inputStreamsub = null;
		boolean chkcv = true;
		Graphics2D gp = null;
		BufferedImage thumbnail = null;

		//이미지 회전 정보
		Metadata metadata = null; // 이미지 메타 데이터 객체
		ExifIFD0Directory directory = null; // 이미지의 Exif 데이터를 읽기 위한 객체
		//ImageWriter writer = null;
		try {
			float qft = defaultScale;
			if(booQtyApply && imageByte != null && imageByte.length > defaultMaxQuality) qft = minScale; //1M 이상일 경우 0.5로 압축

			System.out.println(">>>>> qft >>>>>"+qft);
			System.out.println(">>>>> imageByte.length >>>>>"+imageByte.length);

			iis = ImageIO.createImageInputStream(new ByteArrayInputStream(imageByte));

			Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
			if (readers.hasNext()) {
				ImageReader read = readers.next();
				if(read!=null && fm.contains(read.getFormatName())) chkcv = false;
			}
			inputStream = new ByteArrayInputStream(imageByte);
			bufferedImage = ImageIO.read(inputStream);
			baos = new ByteArrayOutputStream();
			ios = ImageIO.createImageOutputStream(baos);
			inputStream.close();

			//start rotation
			inputStreamsub = new ByteArrayInputStream(imageByte);
			metadata = ImageMetadataReader.readMetadata(inputStreamsub);
			directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
			if(directory != null){
				orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION); // 회전정보
			}
			inputStreamsub.close();
			//end rotation

			ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
		    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		    param.setCompressionQuality(qft);  //scale
		    writer.setOutput(ios);

		    int imgwidth = bufferedImage.getWidth();
		    int imgheight = bufferedImage.getHeight();
			//System.out.println("imgwidth >>>>" + imgwidth);
			//System.out.println("imgheight >>>>" + imgheight);

		    //AffineTransform affineTransform = new AffineTransform();
		    switch (orientation) {
	           case 2: // Flip X
	        	   thumbnail = Scalr.rotate(bufferedImage, Rotation.FLIP_HORZ);
	               break;
	           case 3: // PI rotation
	        	   thumbnail = Scalr.rotate(bufferedImage, Rotation.CW_180);
	               break;
	           case 4: // Flip Y
	        	   thumbnail = Scalr.rotate(bufferedImage, Rotation.FLIP_VERT);
	               break;
	           case 5: // - PI/2 and Flip X
	        	   thumbnail = Scalr.rotate(bufferedImage, Rotation.CW_90);
	        	   thumbnail = Scalr.rotate(bufferedImage, Rotation.FLIP_HORZ);
	               break;
	           case 6: // -PI/2 and -width
	        	   thumbnail = Scalr.rotate(bufferedImage, Rotation.CW_90);
	               break;
	           case 7: // PI/2 and Flip
	        	   thumbnail = Scalr.rotate(bufferedImage, Rotation.CW_90);
	        	   thumbnail = Scalr.rotate(bufferedImage, Rotation.FLIP_VERT);
	               break;
	           case 8: // PI / 2
	        	   thumbnail = Scalr.rotate(bufferedImage, Rotation.CW_270);
	               break;
	           default:
	        	   thumbnail = bufferedImage;
	               break;
	        }
		    //AffineTransformOp op = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
		    //bufferedImage = op.filter(bufferedImage, null);
			//end lotation

	        imgwidth = thumbnail.getWidth();
		    imgheight = thumbnail.getHeight();
		    if(imgwidth > defaultWidth) {
		    	double ratio = (double)defaultWidth/(double)imgwidth;
		    	imgwidth = (int)(imgwidth * ratio);
		    	imgheight = (int)(imgheight * ratio);
			}

		    System.out.println("imgwidth >>>" + imgwidth);
		    System.out.println("imgwidth >>>" + imgheight);

		    // 이미지 리사이즈
            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
		    int imgType = bufferedImage.getType(); //bufferedImage.getType();  TYPE_3BYTE_BGR:5
			if(chkcv) imgType = BufferedImage.TYPE_INT_RGB;
		    Image tmp = thumbnail.getScaledInstance(imgwidth, imgheight, Image.SCALE_SMOOTH);
		    BufferedImage newBufferedImage = new BufferedImage(imgwidth, imgheight, imgType);
		    gp = newBufferedImage.createGraphics();
		    gp.drawImage(tmp, 0, 0, null);
		    gp.dispose();

		    writer.write(null, new IIOImage(newBufferedImage, null, null), param);

			byte[] data = baos.toByteArray();
			if(data != null && data.length > 10) jspByte = data;

			/*
			String decoded = Base64.getEncoder().encodeToString(jspByte);
			String decoded2 = Base64.getEncoder().encodeToString(imageByte);
			System.out.println("default decoded >>> "+decoded2);
			System.out.println("convert decoded >>> "+decoded);
			*/

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("이미지를 변환할 수 없습니다.");
		} finally {
			try {
				//writer.dispose();
				if(gp != null) gp.dispose();
				if(baos != null) baos.close();
				if(ios != null) ios.close();
				if(iis != null) iis.close();
				if(bufferedImage != null) bufferedImage.flush();
				if(thumbnail != null) thumbnail.flush();
				if(inputStream != null) inputStream.close();
				if(inputStreamsub != null) inputStreamsub.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jspByte;
	}
}
