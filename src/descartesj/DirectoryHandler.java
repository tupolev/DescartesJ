package descartesj;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class DirectoryHandler {

        //public event ProgressHandler Progress;
        //public event ProgressHandler Finish;

        //public delegate void ProgressHandler(DirectoryHandler m, ProgressEventArgs e);
        
    private String path = "";
    private String outputDiscardedPath = "";
    private String outputSelectedPath = "";
    private Boolean generateListFileForSelectedFiles = false;
    private Boolean generateListFileForDiscardedFiles = false;
    private Boolean generateFileStructureForSelectedFiles = false;
    private Boolean generateFileStructureForDiscardedFiles = false;
    private String discardedFilesListFileFullName = "";
    private String selectedFilesListFileFullName = "";
    private Boolean keepCopyOfDiscardedFiles = false;
    private Boolean keepCopyOfSelectedFiles = false;
    public ImageList inputList, selectedList, discardedList;
    private File dirInfo;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOutputDiscardedPath() {
        return outputDiscardedPath;
    }

    public void setOutputDiscardedPath(String outputDiscardedPath) {
        this.outputDiscardedPath = outputDiscardedPath;
    }

    public String getOutputSelectedPath() {
        return outputSelectedPath;
    }

    public void setOutputSelectedPath(String outputSelectedPath) {
        this.outputSelectedPath = outputSelectedPath;
    }

    public Boolean getGenerateListFileForSelectedFiles() {
        return generateListFileForSelectedFiles;
    }

    public void setGenerateListFileForSelectedFiles(Boolean generateListFileForSelectedFiles) {
        this.generateListFileForSelectedFiles = generateListFileForSelectedFiles;
    }

    public Boolean getGenerateListFileForDiscardedFiles() {
        return generateListFileForDiscardedFiles;
    }

    public void setGenerateListFileForDiscardedFiles(Boolean generateListFileForDiscardedFiles) {
        this.generateListFileForDiscardedFiles = generateListFileForDiscardedFiles;
    }

    public Boolean getGenerateFileStructureForSelectedFiles() {
        return generateFileStructureForSelectedFiles;
    }

    public void setGenerateFileStructureForSelectedFiles(Boolean generateFileStructureForSelectedFiles) {
        this.generateFileStructureForSelectedFiles = generateFileStructureForSelectedFiles;
    }

    public Boolean getGenerateFileStructureForDiscardedFiles() {
        return generateFileStructureForDiscardedFiles;
    }

    public void setGenerateFileStructureForDiscardedFiles(Boolean generateFileStructureForDiscardedFiles) {
        this.generateFileStructureForDiscardedFiles = generateFileStructureForDiscardedFiles;
    }

    public String getDiscardedFilesListFileFullName() {
        return discardedFilesListFileFullName;
    }

    public void setDiscardedFilesListFileFullName(String discardedFilesListFileFullName) {
        this.discardedFilesListFileFullName = discardedFilesListFileFullName;
    }

    public String getSelectedFilesListFileFullName() {
        return selectedFilesListFileFullName;
    }

    public void setSelectedFilesListFileFullName(String selectedFilesListFileFullName) {
        this.selectedFilesListFileFullName = selectedFilesListFileFullName;
    }

    public Boolean getKeepCopyOfDiscardedFiles() {
        return keepCopyOfDiscardedFiles;
    }

    public void setKeepCopyOfDiscardedFiles(Boolean keepCopyOfDiscardedFiles) {
        this.keepCopyOfDiscardedFiles = keepCopyOfDiscardedFiles;
    }

    public Boolean getKeepCopyOfSelectedFiles() {
        return keepCopyOfSelectedFiles;
    }

    public void setKeepCopyOfSelectedFiles(Boolean keepCopyOfSelectedFiles) {
        this.keepCopyOfSelectedFiles = keepCopyOfSelectedFiles;
    }

    public ImageList getInputList() {
        return inputList;
    }

    public void setInputList(ImageList inputList) {
        this.inputList = inputList;
    }

    public ImageList getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(ImageList selectedList) {
        this.selectedList = selectedList;
    }

    public ImageList getDiscardedList() {
        return discardedList;
    }

    public void setDiscardedList(ImageList discardedList) {
        this.discardedList = discardedList;
    }
        
    public File getDirInfo() {
        return this.dirInfo;
    }

    public void setDirInfo(File dirInfo) {
        this.dirInfo = dirInfo;
    }
        
    public DirectoryHandler(String path) {
        this.setInputList(new ImageList());
        this.setSelectedList(new ImageList());
        this.setDiscardedList(new ImageList());
        this.setPath(path);
    }

    public Integer fillInputList() {
        this.setDirInfo(new File(this.getPath()));
        List<String> files = new ArrayList<String>(Arrays.asList(this.getDirInfo().list()));
        List<String> fileTitles = new ArrayList<String>();  

        for (Integer i = 0; i < files.size(); i++) {
            if (fileTitles.indexOf(DFile.getFileNameWithoutExtension(files.get(i).toString())) < 0)
            {
                fileTitles.add(DFile.getFileNameWithoutExtension(files.get(i).toString()));
            }

        }
        Collections.sort(fileTitles);

        for (String item : fileTitles) {
            files.clear();
            WildcardFileFilter filter = new WildcardFileFilter(item + ".*");

            File[] fileList = this.getDirInfo().listFiles(filter);
            for(File file : fileList) {
                files.add(file.getName());
            }
            List<DFile> fil = new ArrayList<DFile>();
            for (Integer i = 0; i < fileList.length; i++) {
                fil.add(new DFile(
                                        this.getPath(),
                                        fileList[i].getName(),
                                        DFile.getExtension(fileList[i].getName())                                            
                    ));
            }
            this.getInputList().add(new DImage(fil));
        }
        return this.getInputList().count();
    }

    public String getImagePathForItem(int item, String type){
        String path = "";
        if (item >= 0 && item < this.getInputList().count())
        {
            List<DFile> fl = this.getInputList().getList().get(item).getFiles();
            Boolean found = false;

            for (int i = 0; (i < fl.size() && !found); i++)
            {
                if (fl.get(i).getExt() == type)
                {
                    path = fl.get(i).getPath() + java.io.File.pathSeparator + fl.get(i).getName();
                    found = true;
                }
            }

        }
        return path;
    }

    public boolean addToSelectedList(DImage img) {
        return this.getSelectedList().add(img);
    }

    public boolean addToDiscardedList(DImage img) {
        return this.getDiscardedList().add(img);
    }

    public boolean removeFromSelectedList(DImage img) {
        return this.getSelectedList().getList().remove(img);
    }

    public boolean removeFromDiscardedList(DImage img){
        return this.getDiscardedList().getList().remove(img);
    }

    public void checkAndCreateOutputDirs() {
        try
        {
            java.nio.file.Path path = (new java.io.File(this.getOutputDiscardedPath())).toPath();
            if (!Files.isDirectory(path))
                Files.createDirectory(path, null);
            path = (new java.io.File(this.getOutputSelectedPath())).toPath();
            if (!Files.isDirectory(path))
                Files.createDirectory(path);
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    public HashMap<String, Integer> getProcessStats() { 
        HashMap<String, Integer> values = new HashMap();
        int input = 0;
        for (DImage img : this.getInputList().getList())
            for (DFile file : img.getFiles())
                input++;

        int selected = 0;
        for (DImage img : this.getSelectedList().getList())
            for (DFile file : img.getFiles())
                selected++;

        int discarded = 0;
        for (DImage img : this.getDiscardedList().getList())
            for (DFile file : img.getFiles())
                discarded++;

        values.put("input", input);
        values.put("selected", selected);
        values.put("discarded", discarded);
        values.put("ignored", (input - (selected + discarded)));

        return values; 
    }

    public void openExplorerWindow(String directory) {
//            var runExplorer = new System.Diagnostics.ProcessStartInfo();
//            runExplorer.FileName = "explorer.exe";
//            runExplorer.Arguments = directory;
//            System.Diagnostics.Process.Start(runExplorer); 
    }

    public Boolean cleanupProcessVars() {
        Boolean ret = true;
        try
        {
            this.getInputList().getList().clear();
            this.getSelectedList().getList().clear();
            this.getDiscardedList().getList().clear();
            this.setOutputDiscardedPath("");
            this.setOutputSelectedPath("");
            this.setPath("");
        }
        catch (Exception ex) {
            ret = false;
        }
        return ret;
    }

    public void separateFiles(){
            Boolean ret = true;
            Integer totalFiles = 0;
            try
            {
                if (this.getGenerateFileStructureForDiscardedFiles() || this.getGenerateFileStructureForSelectedFiles())
                    this.checkAndCreateOutputDirs();

                //start collect process with discarded files
                java.io.FileWriter fileWriterStream = new java.io.FileWriter(this.getDiscardedFilesListFileFullName());
                java.io.BufferedWriter fileWriter = new java.io.BufferedWriter(fileWriterStream);
                for (DImage item : this.getDiscardedList().getList())
                {
                    for (DFile file : item.getFiles())
                    {
                        //write discarded files list file
                        if (this.getGenerateListFileForDiscardedFiles())
                            fileWriter.write(file.getName());

                        //write discarded files structure
                        if (this.getGenerateFileStructureForDiscardedFiles())
                        {
                            file.move(this.getOutputDiscardedPath() + java.io.File.pathSeparator + file.getName(), this.getKeepCopyOfDiscardedFiles());
                        }
                        totalFiles++;

//                        if (Progress != null)
//                        {
//                            ProgressEventArgs progress = new ProgressEventArgs();
//                            progress.Progress = totalFiles;
//                            Progress(this, progress);
//
//                        }
                    }
                }
                fileWriter.flush();
                fileWriter.close();
                fileWriter = null;       
                fileWriterStream.flush();
                fileWriterStream.close();
                fileWriterStream = null;

                //next collect process with selected files
                fileWriterStream = new java.io.FileWriter(this.getSelectedFilesListFileFullName());
                fileWriter = new java.io.BufferedWriter(fileWriterStream);
                for (DImage item : this.getSelectedList().getList())
                {
                    for (DFile file : item.getFiles())
                    {
                        //selectedFilesPlainList.Add(file.ToString());
                        if (this.getGenerateListFileForSelectedFiles())
                            fileWriter.write(file.getName());

                        if (this.getGenerateFileStructureForSelectedFiles())
                        {
                            //write selected files structure
                            file.move(
                                    this.getOutputSelectedPath() + java.io.File.pathSeparator + file.getName(), this.getKeepCopyOfSelectedFiles());
                        }
                        totalFiles++;

//                        if (Progress != null)
//                        {
//                            ProgressEventArgs progress = new ProgressEventArgs();
//                            progress.Progress = totalFiles;
//                            Progress(this, progress);
//                        }
                    }
                }
                fileWriter.flush();
                fileWriter.close();
                fileWriter = null;       
                fileWriterStream.flush();
                fileWriterStream.close();
                fileWriterStream = null;
                
//                if (Finish != null)
//                {
//                    ProgressEventArgs progress = new ProgressEventArgs();
//                    progress.Progress = totalFiles;
//                    progress.Data.Add("totalFiles",totalFiles);
//                    Finish(this, progress);
//                }
                
            }
            catch (Exception ex)
            {
                
                ret = false;
            }
            //return ret;
        }

    public static String getAppPath() {
        java.io.File currentDir = new java.io.File("");
        return currentDir.getAbsolutePath();
    }
}

    

/*
    public class ProgressEventArgs : EventArgs {
        private Int32 progress = 0;
        public Int32 Progress
        {
            get { return progress; }
            set { progress = value; }
        }

        private Hashtable data;
        public Hashtable Data
        {
            get { return data; }
            set { data = value; }
        }

        public ProgressEventArgs()
        {
            // TODO: Complete member initialization
            this.Data = new Hashtable();
        }
    }
* */    


