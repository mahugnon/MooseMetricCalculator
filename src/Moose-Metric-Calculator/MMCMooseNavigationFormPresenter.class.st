Class {
	#name : #MMCMooseNavigationFormPresenter,
	#superclass : #MMCMooseAbstractQueryPresenter,
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseNavigationFormPresenter class >> runMe [
<example>
self new openWithSpec 
]

{ #category : #accessing }
MMCMooseNavigationFormPresenter >> name [
	^ 'Navigation'
]
