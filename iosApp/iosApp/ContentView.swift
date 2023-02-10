import SwiftUI
import shared

struct ContentView: View {
    @State private var path: NavigationPath = .init()
    
	var body: some View {
        NavigationStack(path: $path, root: {
            LoginScreen(path: $path)
                .navigationDestination(for: ViewOptions.self) { option in
                    option.view($path)
                }
        })
	}
    
    enum ViewOptions{
        case login
        case signup
        case notelist
        //Assign each case with a `View`
        @ViewBuilder func view(_ path: Binding<NavigationPath>) -> some View{
            switch self{
            case .login:
                LoginScreen(path: path)
            case .signup:
                SignupScreen(path: path)
            case .notelist:
                NoteListScreen()
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
