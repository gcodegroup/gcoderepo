package uk.ac.bangor.gcode.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import uk.ac.bangor.gcode.GcodeException;

/**
 * The Class AppLock.
 *
 * @url
 * http://nerdydevel.blogspot.com/2012/07/run-only-single-java-application-instance.html
 * @author rumatoest
 */
public final class AppLock {

    /**
     * The instance.
     */
    private static AppLock instance;

    /**
     * Instantiates a new app lock.
     */
    private AppLock() {
    }

    /**
     * The lock_file.
     */
    private File lock_file = null;

    /**
     * The lock.
     */
    private FileLock lock = null;

    /**
     * The lock_channel.
     */
    private FileChannel lock_channel = null;

    /**
     * The lock_stream.
     */
    private FileOutputStream lock_stream = null;

    /**
     * Instantiates a new app lock.
     *
     * @param key Unique application key
     * @throws Exception The exception
     */
    private AppLock(String key) {

        String tmp_dir = System.getProperty("java.io.tmpdir");

        if (!tmp_dir.endsWith(System.getProperty("file.separator"))) {
            tmp_dir += System.getProperty("file.separator");
        }

        // Acquire MD5
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            String hash_text = new java.math.BigInteger(1, md.digest(key.getBytes())).toString(16);

            // Hash string has no leading zeros
            // Adding zeros to the beginnig of has string
            while (hash_text.length() < 32) {
                hash_text = "0" + hash_text;
            }

            lock_file = new File(tmp_dir + hash_text + ".app_lock");

            // MD5 acquire fail
            if (lock_file == null) {
                lock_file = new File(tmp_dir + key + ".app_lock");
            }

            lock_stream = new FileOutputStream(lock_file);
            String f_content = "Gcode AppLock Object\r\nLocked by key: " + key + "\r\n";
            lock_stream.write(f_content.getBytes());
            lock_channel = lock_stream.getChannel();

            lock = lock_channel.tryLock();
        } catch (NoSuchAlgorithmException | IOException ex) {
            throw new GcodeException(ex);
        }

        if (lock == null) {
            throw new GcodeException("Can't create the application lock");
        }
    }

    /**
     * Release Lock. Now another application instance can gain lock.
     *
     * @throws IOException if an IO error occurs.
     */
    private void release() throws IOException {

        if (lock.isValid()) {
            lock.release();
        }

        if (lock_stream != null) {
            lock_stream.close();
        }

        if (lock_channel.isOpen()) {
            lock_channel.close();
        }

        if (lock_file.exists()) {
            lock_file.delete();
        }
    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable {
        this.release();
        super.finalize();
    }

    /**
     * Set application lock. Method can be run only one time per application.
     * All next calls will be ignored.
     *
     * @param key Unique application lock key
     * @return true, if successful
     */
    public static boolean setLock(String key) {

        if (instance != null) {
            return true;
        }

        try {
            instance = new AppLock(key);
        } catch (Exception ex) {
            instance = null;
            throw new GcodeException("Fail to set the application lock.\n"
                    + "Only one application instance may run at the same time!\n"
                    + "Check if you have another instance opened.\n "
                    + "The problem may persist if your applicaiton did NOT terminate normally last time.\n"
                    + "In this case you will have to restart your computer.", ex);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                AppLock.releaseLock();
            }
        });

        return true;
    }

    /**
     * Trying to release Lock. After release you can not user AppLock again in
     * this application.
     *
     */
    public static void releaseLock() {

        try {

            if (instance == null) {
                return;
            }

            instance.release();

        } catch (IOException ex) {

            throw new GcodeException("Failed to release the application lock", ex);
        }
    }
}
