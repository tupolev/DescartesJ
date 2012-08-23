package descartesj;

import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class SeparateFilesTask extends SwingWorker<Boolean, Integer> {
     
    private DirectoryHandler dh;

    public DirectoryHandler getDh() {
        return dh;
    }

    public void setDh(DirectoryHandler dh) {
        this.dh = dh;
    }

    SeparateFilesTask(DirectoryHandler dh) {
        //initialize
        super();
        this.setDh(dh);
    //    this.setPb(pbar);
    }

     @Override
     public Boolean doInBackground() throws Exception{
         
        Boolean ret = true;
        Integer totalFiles = 0;
        try
        {
            if (this.getDh().getGenerateFileStructureForDiscardedFiles() || this.getDh().getGenerateFileStructureForSelectedFiles())
                this.getDh().checkAndCreateOutputDirs();

            //start collect process with discarded files
            java.io.FileWriter fileWriterStream = new java.io.FileWriter(this.getDh().getDiscardedFilesListFileFullName());
            java.io.BufferedWriter fileWriter = new java.io.BufferedWriter(fileWriterStream);
            for (DImage item : this.getDh().getDiscardedList().getList())
            {
                for (DFile file : item.getFiles())
                {
                    //write discarded files list file
                    if (this.getDh().getGenerateListFileForDiscardedFiles())
                        fileWriter.write(file.getName()+"\n");

                    //write discarded files structure
                    if (this.getDh().getGenerateFileStructureForDiscardedFiles())
                    {
                        file.move(this.getDh().getOutputDiscardedPath() + java.io.File.separator + file.getName(), 
                                this.getDh().getKeepCopyOfDiscardedFiles());
                    }
                    totalFiles++;
                    publish(totalFiles);
                }
            }
            fileWriterStream.flush();
            fileWriter.flush();
            fileWriterStream.close();
            fileWriter.close();
            fileWriterStream = null;
            fileWriter = null;      


            //next collect process with selected files
            fileWriterStream = new java.io.FileWriter(this.getDh().getSelectedFilesListFileFullName());
            fileWriter = new java.io.BufferedWriter(fileWriterStream);
            for (DImage item : this.getDh().getSelectedList().getList())
            {
                for (DFile file : item.getFiles())
                {
                    //selectedFilesPlainList.Add(file.ToString());
                    if (this.getDh().getGenerateListFileForSelectedFiles())
                        fileWriter.write(file.getName() + "\n");

                    if (this.getDh().getGenerateFileStructureForSelectedFiles())
                    {
                        //write selected files structure
                        file.move(
                                this.getDh().getOutputSelectedPath() + java.io.File.separator + file.getName(), 
                                this.getDh().getKeepCopyOfSelectedFiles());
                    }
                    totalFiles++;
                    publish(totalFiles);
                    setProgress(totalFiles);
                }
            }
            fileWriterStream.flush();
            fileWriter.flush();
            fileWriterStream.close();
            fileWriter.close();
            fileWriterStream = null;
            fileWriter = null;       

            publish(totalFiles);
            setProgress(totalFiles);
        }
        catch (Exception ex)
        {
            ret = false;
        }
        return ret;
     }
}