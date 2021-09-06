package controller;

public abstract class ViewController {

    protected ViewController() {
        on_mounted();
    }

    /**
     * 
     * <p>
     * <em>[Bad Approach !!]</em> <br/>
     * But I'm too lazy to set up <strong>Spring's Depedency Injection</strong> 😝
     * <br/>
     * </p>
     * BTW : With .DOTNET we have built-in DI Contaier/Dependency Injection for IoC
     * and it's better 😎
     */
    protected abstract void on_mounted();
}
