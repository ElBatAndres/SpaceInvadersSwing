import co.edu.uptc.model.GameManagement;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.utils.ConfigManager;
import co.edu.uptc.view.MainView;

public class Main {
    public static void main(String[] args) {
        new ConfigManager();
        Presenter presenter = new Presenter();
        GameManagement model = new GameManagement();
        MainView view = new MainView();

        presenter.setView(view);
        presenter.setModel(model);

        model.setPresenter(presenter);

        view.setPresenter(presenter);
        view.begin();
    }
}