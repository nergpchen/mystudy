package com.mystudy.study.java.nio;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;


/**
 * 引用资料：http://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 * http://www.iteye.com/magazines/132-Java-NIO#579
 * 学习NIO框架
 * 1：框架结构
 * 2:核心概念：
 * 3：NIO的特点有哪些？
 * 4：哪些核心类？
 * 5：哪些基类：
 * 6：源码分析：
 * @author Administrator
 *
 */
public class StudyNIO {

	/**
	 * 框架学习：
	 * NIO的框架目的是为了提供更快速的IO操作。
	 * 1:核心组件：NIO的核心组件包括
	 * Channels,Buffers,Selectors
	 * Channels:是NIO框架的基础，在新的IO处理中是以通道为对象的，而传统的IO是以字节流或者字符流作为基础的。那么通道到底是什么呢？有什么特性呢？
	 * 	
	 * 
	 */
	public void framkeOFNIO(){
		
	}
	
	/**
	 * 通道的基本概念：
	 * Channels:是NIO框架的基础，在新的IO处理中是以通道为对象的，而传统的IO是以字节流或者字符流作为基础的。那么通道到底是什么呢？有什么特性呢？
	 * 我们操作都是以通道为基础的，通道可以是双向的。
	 * 定义了1个Channel接口：Channel extends Closeable，只有两个方法isopen(),close()
	 * 常见的实现类：
	 * FileChannel:从文件中读写数据
	 * DatagramChannel:能通过UDP读写网络中的数据
	 * SocketChannel:能通过TCP读写网络中的数据
	 * ServerSocketChannel:可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
	 * FileChannel:
	 * FileChannel的实现类是FileChannelImpl，通常我们通过RandomAccessFile.getChannel()来获取一个文件的FileChannel对象。
	 * 这个时候的FileChannel的状态是打开状态,isOpen()来判断通道是否打开。
	 * 如果要读取文件数据：
			ByteBuffer buf = ByteBuffer.allocate(48);
			int bytesRead = inChannel.read(buf);
			把文件的内容写入到ByteBuffer对象中.那么这个对象又是什么呢?从字面上理解是字节缓冲池,这个是NIO里一个重要的组件Buffers缓冲区，通过缓冲区来减缓I/O操作的次数，提高速度.
	       如果要写入文件数据：
	       ByteBuffer buf = ByteBuffer.allocate(48)
	       buf.clear();
	       buf.put();
	 * 	   fileChannel.write(buf);
	 * 
	 * @throws IOException 
	 * 
	 */
	@Test
	public void whatIsChannel() throws IOException{
		
		RandomAccessFile file = null;
		try {
		file = new RandomAccessFile("D://test/test.txt", "rw");
		ByteBuffer dst =ByteBuffer.allocate(48);
		FileChannel fileChannel = file.getChannel();
		
		if(!fileChannel.isOpen())
			return ;
		
		fileChannel.read(dst);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(file!=null )
			file.close();
		}
	}

	/**http://ifeve.com/buffers/#basicusage
	 *Buffer学习：
	 *Buffer就是缓冲区，实际上缓冲区就是一个包含在对象里的基本数据元素数组.在NIO中，通道的数据会保存在缓冲区中，读取数据实际是从缓冲区读取。
	 *缓冲区的属性：
	 *容量：Capacity，
	 *上界：Limit;位置：Position;标记：Mark 
	 *特点：
	 *Buffer有写和读两种模式
	 *实现类：
	 *ByteBuffer
	MappedByteBuffer
	CharBuffer
	DoubleBuffer
	FloatBuffer
	IntBuffer
	LongBuffer
	ShortBuffer
	 */
	public void wahtIsBuffers(){
		RandomAccessFile file = null;
		try {
		file = new RandomAccessFile("D://test/test.txt", "rw");
		ByteBuffer dst =ByteBuffer.allocate(48);
		FileChannel fileChannel = file.getChannel();
		
		if(!fileChannel.isOpen())
			return ;
		
		fileChannel.read(dst);
		//从缓冲区读出数据
		while(dst.hasRemaining()){
			dst.get();
		}
		
		//清楚缓冲区
		dst.clear();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(file!=null )
				try {
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	/**http://ifeve.com/selectors/
	 * Selector学习：
	 * 1：Selector是什么？
	 * 2：Selector的重要属性：
	 * 3:Selectro的重要方法：
	 * 4:相关实现类
	 * 
	 */
	public void whatIsSelector(){
		
	}
}
