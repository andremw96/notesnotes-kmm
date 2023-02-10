//
//  LoginScreen.swift
//  iosApp
//
//  Created by Quipper Indonesia on 16/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginScreen: View {
    @StateObject private var viewModel = ViewModelProvider().provideLoginViewModel()
    @State private var isPresentingAlert: Bool = false
    @Binding var path: NavigationPath
    
    var body: some View {
        let isPresentingAlert = Binding<Bool>(
            get: { self.viewModel.viewState.isLoginError == true },
            set: { _ in self.viewModel.viewState.loginError = "" }
        )
        
        VStack{
            Text("Noted")
                .font(.largeTitle)
                .padding([.top, .bottom], 40)
                .shadow(radius: 10.0, x: 20, y: 10)
            
            VStack (
                alignment: .leading,
                spacing: 15
            ) {
                TextFieldWithPromptView(
                    sfSymbolName: "person",
                    placeholder: "Username",
                    text: $viewModel.viewState.username,
                    prompt: viewModel.viewState.usernameError ?? ""
                ).autocapitalization(.none)
                 .onChange(of: viewModel.viewState.username, perform: { char in
                     viewModel.validateUsernamePassword(username: char, password: viewModel.viewState.password)
                 })
                 .disabled(viewModel.viewState.isLoading)
                
                PasswordView(
                    placeholder: "Password",
                    text: $viewModel.viewState.password,
                    prompt: viewModel.viewState.passwordError ?? ""
                ).onChange(of: viewModel.viewState.password, perform: { char in
                    viewModel.validateUsernamePassword(username: viewModel.viewState.username, password: char)
                })
                .disabled(viewModel.viewState.isLoading)
            }.padding([.leading, .trailing], 27.5)
            
            VStack {
                Button(
                    action: {
                        Task {
                            await viewModel.login(username: viewModel.viewState.username, password: viewModel.viewState.password) {
                                path.append(ContentView.ViewOptions.notelist)
                            }
                        }
                    }
                ) {
                    HStack {
                        Text("Login")
                            .font(.headline)
                            .foregroundColor(.white)
                        
                        if viewModel.viewState.isLoading {
                            ProgressView()
                        }
                    }.padding()
                        .frame(width: 300, height: 50)
                        .background(Color.green)
                        .cornerRadius(15.0)
                        .shadow(radius: 10.0, x: 20, y: 10)
                }.padding(.top, 50)
                    .disabled(viewModel.viewState.isLoading)
                
                Button("Create New Account") {
                    path.append(ContentView.ViewOptions.signup)
                }.foregroundColor(Color.white)
            }
            
            Spacer()
        }.background(
            LinearGradient(
                gradient: Gradient(
                    colors: [.purple, .blue]
                ), startPoint: .top, endPoint: .bottom
            ).edgesIgnoringSafeArea(.all)
        )
        .alert(isPresented: isPresentingAlert, content: {
            Alert(title: Text(viewModel.viewState.loginError))
        })
        .onAppear {
            // user already login
            Task {
                await viewModel.checkLogin {
                    path.append(ContentView.ViewOptions.notelist)
                }
            }
        }
    }
}

struct LoginScreen_Previews: PreviewProvider {
    @State private static var path: NavigationPath = .init()
    
    static var previews: some View {
        NavigationStack(path: $path, root: {
            LoginScreen(path: $path)
        })
    }
}
