package by.oshmianski.loaders;

import by.oshmianski.objects.ParserZipCode;
import by.oshmianski.ui.edt.UIProcessor;
import by.oshmianski.utils.MyLog;

public class LoadIndex implements Runnable, Loader {
    private boolean executed = false;
    private boolean canceled = false;

    /**
     * UI callback
     */
    private UIProcessor ui;

    private String moduleName;
    private String reestr;

    public LoadIndex(UIProcessor ui) {
        super();

        this.ui = ui;

        moduleName = "Загрузка данных";
    }

    /**
     * Starts this loader execution in separate thread
     */
    @Override
    public synchronized void execute() {
        if (executed) {
//            throw new IllegalStateException("Loader is already executed");
            return;
        }
        executed = true;
        canceled = false;
        Thread t = new Thread(this, "Custom loader thread " + moduleName);
        t.start();
    }

    /**
     * Loader main cycle
     */
    @Override
    public void run() {
        //действия вначале
        ui.setStartEnable(false);

        try {
            work();
        } catch (Exception e) {
            MyLog.add2Log(e);
        } finally {
            //окончание
            executed = false;
            ui.setStartEnable(true);
        }
    }

    /**
     * Cancels current loading
     */
    @Override
    public synchronized void cancel() {
        canceled = true;
    }

    public synchronized boolean isCanceled() {
        return canceled;
    }

    protected void work() {
        ui.clearIndex();

        if (ui.getParserType().equalsIgnoreCase("zip_code")) {
            ParserZipCode parserZipCode = new ParserZipCode(ui.getFolderPath(), getUi(), this);
            parserZipCode.process();
        }
    }

    public UIProcessor getUi() {
        return ui;
    }

    public boolean isExecuted() {
        return executed;
    }

    public String getReestr() {
        return reestr;
    }

    public void setReestr(String reestr) {
        this.reestr = reestr;
    }
}
